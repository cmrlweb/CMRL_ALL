@extends('app')

@section('content')
<div class="container">
	<div class="row">
		<div class="col-md-5"></div>
		<div class="col-md-3">
            <h3>Search</h3>
        </div>
        <div class="col-md-4"></div>
    </div>
	
	{!! Form::open(array('url' => '#', 'class' => 'form-horizontal')) !!}
	<!-- Date Picker-->
    <div class="row">
            <div class="form-group">
            	<div class="control-label col-md-3">From:</div>
            	<div class="input-group registration-date-time com-md-5">
            		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
            		<input class="form-control" name="registration_date" id="registration-date" type="date">
            	</div>
            </div>
	</div>

        <!-- Search Box -->
    <div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-5">
            <div class="input-group form-group">
              {!!Form::text('searchcontent',null,array('class' => 'form-control'))!!}
              	<span class="input-group-btn">
              		<button class="btn btn-default" value="search" type="submit">
              			<span class="glyphicon glyphicon-search"></span>
             		</button>
             	</span>
             </div>
        </div>
        <div class="col-md-3"></div>
        
	</div>
	{!! Form::close() !!}


	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-heading">Results</div>
				{!! Form::open(array('url' => '/report')) !!}
				<div class="panel-body">
					@if (isset($data))
						@if($data != NULL)
						@foreach ($data as $index => $history)
						<ul>
							<li>{{$history->id}}  {{$history->assetcode}} {{$history->username}} {{$history->created_at}} {{$history->status}} 
								@if ($history->status == "CHANGED")
								<input type="hidden" name="assetcode" value="{{ $history->assetcode }}"> 
								<input type="hidden" name="username" value="{{ $history->username }}">
								{!!Form::submit('Generate Report') !!}
								@endif
							</li>
						</ul>
						@endforeach
						@else
							<h5>No Data</h5>
						@endif
					@else
						<h5>Enter Asset Code </h5>
					@endif
				</div>
				{!! Form::close() !!}
			</div>
		</div>
	</div>
</div>
@endsection